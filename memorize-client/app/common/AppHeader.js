import React from "react";
import {Link} from "react-router-dom";

export default class AppHeader extends React.Component {
    constructor(props) {
        super(props);
        this.showWordAppender = this.showWordAppender.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
    }

    handleLogout() {
        this.props.handleLogout();
    }

    showWordAppender() {
        $(".ui.modal")
            .modal({
                closable: true,
                transition: "horizontal flip",
                onDeny: function () {
                    return true;
                },
                onApprove: function () {
                }
            })
            .modal("show");
    }

    render() {
        const isAuthenticated = this.props.isAuthenticated;
        const currentUser = this.props.currentUser;
        return (
            <div className="ui huge fixed borderless teal menu">
                <div className="ui text container">
                    <Link className="header item" to="/">
                        <i className="pencil alternate icon"/>
                        Memorize
                    </Link>
                    <a href="#" className="item" onClick={this.showWordAppender}>
                        New word
                    </a>
                    <Link className="item" to="/training">
                        Training
                    </Link>

                    <div className="ui right item">
                        {isAuthenticated ? (
                            <div className="ui simple dropdown item" tabIndex="0">
                                {currentUser.name}
                                <i className="dropdown icon"></i>
                                <div className="menu" tabIndex="-1">
                                    <div className="item" onClick={this.handleLogout}>Logout</div>
                                </div>
                            </div>
                        ) : (
                            <Link className="item" to="/login">Login</Link>
                        )}
                    </div>
                </div>
            </div>
        );
    }
}
