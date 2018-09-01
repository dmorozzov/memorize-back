import './semantic/semantic.min.js';
import './semantic/components/modal.min.js';

import './semantic/semantic.min.css';
import './semantic/components/modal.min.css';
import './main.css';

import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Words from "./components/Words";
import Header from "./components/Header";
import NotFound from "./components/NotFound";

export default class App extends Component {
    render() {
        return (
            <Router>
                <div>
                <Header/>
                <div className="ui main container shift_content">
                  <Switch>
                      <Route exact path='/' component={Words}/>
                      <Route path='*' component={NotFound}/>
                  </Switch>
                </div>
                </div>
            </Router>
        )
    }
}
