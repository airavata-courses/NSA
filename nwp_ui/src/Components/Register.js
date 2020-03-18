import React, { Component } from "react";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import AppBar from "material-ui/AppBar";
import RaisedButton from "material-ui/RaisedButton";
import TextField from "material-ui/TextField";
import { Button, Form, FormGroup, Label, Input } from "reactstrap";
import axios from "axios";
import App from "../App";

class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userID: "",
      firstName: "",
      lastName: "",
      emailId: "",
      password: ""
    };
  }

  handleChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleSubmit = event => {
    event.preventDefault();
    const user = {
      userID: this.state.username,
      firstName: this.state.firstName,
      lastName: this.state.lastName,
      email: this.state.emailId,
      password: this.state.password
    };

  
     const jsonobj = JSON.stringify(user);

    axios
      .post("http://localhost:8081/register", JSON.parse(jsonobj))
      .then(res => {
        console.log(res.data);
        if (res.data == "REGISTER_SUCCESS") {
          this.props.views.login();
        }
      });
  };
  render() {
    return (
      <Form className="register-form" onSubmit={this.handleSubmit}>
        <h1>REGISTER PORTAL</h1>
        <FormGroup>
          <Label>name</Label>
          <Input
            type="name"
            placeholder="name"
            name="username"
            value={this.state.username}
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
        <FormGroup>
          <Label>emailId</Label>
          <Input
            type="email"
            placeholder="email"
            name="emailId"
            value={this.state.emailId}
            onChange={this.handleChange}
          />
        </FormGroup>
        <FormGroup>
          <Label>First name</Label>
          <Input
            type="name"
            placeholder="first name"
            name="firstName"
            value={this.state.firstName}
            onChange={this.handleChange}
          />
        </FormGroup>
        <FormGroup>
          <Label>Last name</Label>
          <Input
            type="name"
            placeholder="last name"
            name="lastName"
            value={this.state.lastName}
            onChange={this.handleChange}
          />
        </FormGroup>

        <Button className="btn-lg btn-dark btn-block" type="submit">
          Register
        </Button>
        <Button className="btn-lg btn-dark btn-block" onClick={this.props.views.login}>
          Back to login
        </Button>
      </Form>
    );
  }
}
const style = {
  margin: 15
};
export default Register;
