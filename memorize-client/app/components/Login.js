import React, {Component} from 'react';
import {login} from '../util/APIUtils';
import {ACCESS_TOKEN} from '../util/constants';

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = { login: "", password: "" };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const value = target.type === "checkbox" ? target.checked : target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        self = this;
        event.preventDefault();
        const loginRequest = {usernameOrEmail: self.state.login, password:  self.state.password};
        login(loginRequest,
            response => {
                localStorage.setItem(ACCESS_TOKEN, response.data.accessToken);
                this.props.onLogin();
            }, error => {
                if (error.status === 401) {
                    console.log('Your Username or Password is incorrect. Please try again!');
                } else {
                    console.log('Sorry! Something went wrong. Please try again!');
                }
            });
    }

    render() {
        return (
            <div className="column">
                <h2 className="ui teal image header">
                    <img src="assets/images/logo.png" className="image"/>
                    <div className="content">
                        Log-in to your account
                    </div>
                </h2>
                <form className="ui large form">
                    <div className="ui stacked segment">
                        <div className="field">
                            <div className="ui left icon input">
                                <i className="user icon"></i>
                                <input type="text" name="login" placeholder="E-mail address or User name"
                                       value={this.state.login}
                                       onChange={this.handleChange} />
                            </div>
                        </div>
                        <div className="field">
                            <div className="ui left icon input">
                                <i className="lock icon"></i>
                                <input type="password" name="password" placeholder="Password"
                                       value={this.state.password}
                                       onChange={this.handleChange} />
                            </div>
                        </div>
                        <div className="ui fluid large teal submit button" onClick={this.handleSubmit}>Login</div>
                    </div>

                    <div className="ui error message"></div>

                </form>

                <div className="ui message">
                    New to us? <a href="#">Sign Up</a>
                </div>
            </div>
        );
    }
}
//
// class LoginForm extends Component {
//     constructor(props) {
//         super(props);
//         this.handleSubmit = this.handleSubmit.bind(this);
//     }
//
//     handleSubmit(event) {
//         event.preventDefault();
//         this.props.form.validateFields((err, values) => {
//             if (!err) {
//                 const loginRequest = Object.assign({}, values);
//                 login(loginRequest)
//                     .then(response => {
//                         localStorage.setItem(ACCESS_TOKEN, response.accessToken);
//                         this.props.onLogin();
//                     }).catch(error => {
//                     if (error.status === 401) {
//                         notification.error({
//                             message: 'Polling App',
//                             description: 'Your Username or Password is incorrect. Please try again!'
//                         });
//                     } else {
//                         notification.error({
//                             message: 'Polling App',
//                             description: error.message || 'Sorry! Something went wrong. Please try again!'
//                         });
//                     }
//                 });
//             }
//         });
//     }
//
//     render() {
//         const {getFieldDecorator} = this.props.form;
//         return (
//             <Form onSubmit={this.handleSubmit} className="login-form">
//                 <FormItem>
//                     {getFieldDecorator('usernameOrEmail', {
//                         rules: [{required: true, message: 'Please input your username or email!'}],
//                     })(
//                         <Input
//                             prefix={<Icon type="user"/>}
//                             size="large"
//                             name="usernameOrEmail"
//                             placeholder="Username or Email"/>
//                     )}
//                 </FormItem>
//                 <FormItem>
//                     {getFieldDecorator('password', {
//                         rules: [{required: true, message: 'Please input your Password!'}],
//                     })(
//                         <Input
//                             prefix={<Icon type="lock"/>}
//                             size="large"
//                             name="password"
//                             type="password"
//                             placeholder="Password"/>
//                     )}
//                 </FormItem>
//                 <FormItem>
//                     <Button type="primary" htmlType="submit" size="large" className="login-form-button">Login</Button>
//                     Or <Link to="/signup">register now!</Link>
//                 </FormItem>
//             </Form>
//         );
//     }
// }

export default Login;