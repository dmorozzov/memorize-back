import './semantic/semantic.min.js';
import './semantic/components/modal.min.js';

import './semantic/semantic.min.css';
import './semantic/components/modal.min.css';
import './main.css';

import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Words from "./components/Words";
import AppHeader from "./components/AppHeader";
import NotFound from "./components/NotFound";
import Login from "./components/Login";

import {getCurrentUser} from './util/APIUtils';

export default class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: null,
            isAuthenticated: false,
            isLoading: false
        };
        this.handleLogin = this.handleLogin.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
        this.loadCurrentUser = this.loadCurrentUser.bind(this);
    }

    loadCurrentUser() {
        this.setState({
            isLoading: true
        });
        getCurrentUser()
            .then(response => {
                this.setState({
                    currentUser: response,
                    isAuthenticated: true,
                    isLoading: false
                });
            }).catch(error => {
            this.setState({
                isLoading: false
            });
        });
    }

    componentWillMount() {
        this.loadCurrentUser();
    }

    handleLogout(redirectTo = "/", notificationType = "success", description = "You're successfully logged out.") {
        localStorage.removeItem(ACCESS_TOKEN);

        this.setState({
            currentUser: null,
            isAuthenticated: false
        });

        this.props.history.push(redirectTo);
    }

    handleLogin() {
        this.loadCurrentUser();
        this.props.history.push("/");
    }

    render() {
        return (
            <Router>
                <div>
                    <AppHeader/>
                    <div className="ui main container shift_content">
                        <Switch>
                            <Route exact path='/' render={(props) => <Words isAuthenticated={this.state.isAuthenticated}
                                                                            currentUser={this.state.currentUser}
                                                                            handleLogout={this.handleLogout} {...props}/> }/>
                            <Route path="/login"
                                   render={(props) => <Login onLogin={this.handleLogin} {...props} />}/>
                            <Route path='*' component={NotFound}/>
                        </Switch>
                    </div>
                </div>
            </Router>
        )
    }
}
