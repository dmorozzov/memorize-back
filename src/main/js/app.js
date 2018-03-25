'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');
const $ = require("jquery");
const semantic = require('./semantic.min.js');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {words: []};
    }

    fetchWords() {
        return axios({
            method: 'post',
            url: '/word/list',
            data: {}
        });
    };

    saveWord(word) {
        return axios({
            method: 'post',
            url: '/word/save',
            data: word
        });
    };


    componentDidMount() {
        const self = this;
        self.fetchWords().then(result => {
            self.setState({
                words: result.data
            });
        })
    }

    handleClick(word) {
        const self = this;
        word.userId = 1;
        self.saveWord(word).then(result => {
            self.setState({
                words: self.state.words.concat([result.data])
            });
        }).catch(function (error) {
            alert(error);
        });
    }

    render() {
        return (
            <div className="ui text container">
                <div className="ui segment">
                    <Appender onClick={(word) => this.handleClick(word)}/>
                    <h4 className="ui horizontal divider header">
                        Saved words
                    </h4>
                    <WordList words={this.state.words}/>
                </div>
            </div>
        )
    }
}

class Appender extends React.Component {

    constructor(props) {
        super(props);
        this.state = {original: '', translation: ''};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        this.props.onClick(this.state);
        event.preventDefault();
    }

    render() {
        return (
            <form className="ui small form" onSubmit={this.handleSubmit}>
                <div className="two fields">
                    <div className="required field">
                        <label>Original:</label>
                        <input type="text" name="original" value={this.state.original} onChange={this.handleChange}/>
                    </div>
                    <div className="required field">
                        <label>Translation:</label>
                        <input type="text" name="translation" value={this.state.translation} onChange={this.handleChange}/>
                    </div>
                </div>
                <input className="ui positive basic button" type="submit" value="Submit"/>
            </form>
        );
    }
}

class WordList extends React.Component {

    render() {
        var words = this.props.words.map(word =>
            <Word key={word.id} word={word}/>
        );
        return (
            <table className="ui single line selectable table">
                <thead>
                    <tr>
                        <th>Original Word</th>
                        <th>Translation</th>
                        <th>Created date</th>
                    </tr>
                </thead>
                <tbody>
                    {words}
                </tbody>
            </table>
        )
    }
}


class Word extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.word.original}</td>
                <td>{this.props.word.translation}</td>
                <td>{this.props.word.createDate}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('react')
)


