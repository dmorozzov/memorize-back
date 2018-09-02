import React from "react";

import Resources from "../util/ApiUtils.js";
import WordList from "./WordList.js";
import WordAddDialog from "./WordAddDialog.js";

export default class Words extends React.Component {
    constructor(props) {
        super(props);
        this.loadData = this.loadData.bind(this);
        this.handleOnScroll = this.handleOnScroll.bind(this);
        this.onAddWord = this.onAddWord.bind(this);
        this.reloadAll = this.reloadAll.bind(this);

        this.state = {
            words: [],
            userId: 1,
            pageInfo: {
                requestSent: false,
                pageNumber: 0,
                pageSize: 20
            }
        };
    }

    componentDidMount() {
        window.addEventListener("scroll", this.handleOnScroll);
        this.loadData();
    }

    componentWillUnmount() {
        window.removeEventListener("scroll", this.handleOnScroll);
    }

    handleOnScroll() {
        var scrollTop =
            (document.documentElement && document.documentElement.scrollTop) ||
            document.body.scrollTop;
        var scrollHeight =
            (document.documentElement && document.documentElement.scrollHeight) ||
            document.body.scrollHeight;
        var clientHeight =
            document.documentElement.clientHeight || window.innerHeight;
        var scrolledToBottom = Math.ceil(scrollTop + clientHeight) >= scrollHeight;

        if (scrolledToBottom) {
            this.loadData();
        }
    }

    loadData() {
        const self = this;
        if (self.state.pageInfo.requestSent || self.state.pageInfo.endReached) {
            return;
        }
        let request = {
            pageNumber: self.state.pageInfo.pageNumber,
            pageSize: self.state.pageInfo.pageSize,
            userId: self.state.userId
        };

        this.setState(
            (prevState, props) => {
                return {
                    pageInfo: {
                        ...prevState.pageInfo,
                        requestSent: true
                    }
                };
            },
            () =>
                Resources.fetchWords(request).then(result =>
                    self.updateWordList(result)
                )
        );
    }

    updateWordList(result) {
        let data = result.data;
        if (this.state.endReached) {
            return;
        }
        this.setState((prevState, props) => {
            return {
                words: prevState.words.concat(data.content),
                pageInfo: {
                    ...prevState.pageInfo,
                    requestSent: false,
                    pageNumber: ++data.number,
                    numberOfElements: data.numberOfElements,
                    totalElements: data.totalElements,
                    endReached: data.last
                }
            };
        });
    }

    onAddWord(word) {
        const self = this;
        let request = {word: word, userId: self.state.userId};
        Resources.saveWord(request)
            .then(result => {
                if (result.data.status === "ERROR") {
                    console.log("ERROR: " + result.data.message);
                } else {
                    this.reloadAll();
                }
            })
            .catch(function (error) {
                alert(error);
            });
    }

    reloadAll() {
        this.setState(
            prevState => {
                return {
                    words: [],
                    pageInfo: {
                        ...prevState.pageInfo,
                        requestSent: false,
                        pageNumber: 0,
                        numberOfElements: 0,
                        totalElements: 0,
                        endReached: false
                    }
                };
            },
            () => this.loadData()
        );
    }

    render() {
        return (
            <div>
                <WordAddDialog onClick={word => this.onAddWord(word)}/>
                <WordList words={this.state.words} pageInfo={this.state.pageInfo}/>
            </div>
        );
    }
}
