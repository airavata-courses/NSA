import React from "react";
import { Button, Form, FormGroup, Label, Input } from "reactstrap";

import { FacebookLoginButton } from "react-social-login-buttons";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import axios from "axios";
import App from "../App";

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      userName: "Shivali"
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
      .post("http://localhost:8081/login", [
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
          this.props.changeView();
        }
      });
  };

  render() {
    return (
      <Form className="login-form" onSubmit={this.handleSubmit}>
        <h1>LOGIN PORTAL</h1>
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
        <Button className="btn-lg btn-dark btn-block">Register</Button>
      </Form>
    );
  }
}

export default Login;
