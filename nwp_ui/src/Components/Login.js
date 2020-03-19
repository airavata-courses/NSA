import React from "react";
import { Button, Form, FormGroup, Label, Input, Alert} from "reactstrap";

import { FacebookLoginButton } from "react-social-login-buttons";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import axios from "axios";
import App from "../App";

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      alerts: null,
      name: "name"
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  // componentDidMount(){
  //   axios.get('').then(res)
  // }

  handleChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleSubmit = event => {
    event.preventDefault();
    const user = {
      username: this.state.name,
      firstName: "null",
      lastName: "null",
      emailId: "null",
      password: this.state.password
    };

    const jsonobj = JSON.stringify(user);

    axios
      .post("http://apigateway_service:8081/login", [
        user.username +
          " " +
          user.firstName +
          " " +
          user.lastName +
          " " +
          user.emailId +
          " " +
          user.password
      ])
      .then(res => {
        console.log(res.data);
        if (res.data == "success") {
          this.props.views.dashboard();
        }
        else {
          this.setState({
            alerts: <Alert color="danger">Login Failed</Alert>
          })
        }
      });
  };

  render() {
    return (
      <Form className="login-form" onSubmit={this.handleSubmit}>
        {this.state.alerts}
        <div className="h1">Login Portal</div>
        <FormGroup>
          <Label>name</Label>
          <Input
            type="name"
            placeholder="name"
            name="name"
            value={this.state.name}
            onChange={this.handleChange}
          />
        </FormGroup>
        <FormGroup>
          <Label>Password</Label>
          <Input
            type="password"
            placeholder="password"
            name="password"
            value={this.state.password}
            onChange={this.handleChange}
          />
        </FormGroup>

        {/* <Link to="/Login"> */}
        <Button className="btn-lg btn-dark btn-block" type="submit">
          Login
        </Button>
        {/* </Link> */}
        {/* 
        <div className="text-center pt-3">
          Or continue with your social account
        </div>
        <FacebookLoginButton className="mt-3 mb-3" /> */}
        <Button className="btn-lg btn-dark btn-block" onClick={this.props.views.register}>Register</Button>
      </Form>
    );
  }
}

export default Login;
