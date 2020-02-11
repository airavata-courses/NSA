import React from "react";

import "../App.css";
import { Button, Form, FormGroup, Label, Input } from "reactstrap";
import { Link } from "react-router";
class Dashboard extends React.Component {
  handleChange = event => {
    this.setState({ name: event.target.value });
  };

  handleSubmit = event => {
    event.preventDefault();
    const user = {
      userName: this.state.name,
      firstName: "shiv",
      lastName: "Jejurkar",
      emailId: "null"
    };

    const jsonobj = JSON.stringify(user);
    const Col = 1;

    // axios.post("http://localhost:8081/login", { user }).then(res => {
    //   console.log(res.data);
    //   if (res.data) {
    //     <div>
    //       <Link to="/Register"> </Link>;
    //     </div>;
    //   }
    // });
  };

  render() {
    return (
      <Form>
        <Form.Row>
          <Form.Group controlId="formGridState">
            <Form.Label>State</Form.Label>
            <Form.Control as="select">
              <option>Choose...</option>
              <option>...</option>
            </Form.Control>
          </Form.Group>

          <Form.Group controlId="formGridState">
            <Form.Label>State</Form.Label>
            <Form.Control as="select">
              <option>Choose...</option>
              <option>...</option>
            </Form.Control>
          </Form.Group>

          <Form.Group controlId="formGridState">
            <Form.Label>State</Form.Label>
            <Form.Control as="select">
              <option>Choose...</option>
              <option>...</option>
            </Form.Control>
          </Form.Group>

          <Form.Group controlId="formGridState">
            <Form.Label>State</Form.Label>
            <Form.Control as="select">
              <option>Choose...</option>
              <option>...</option>
            </Form.Control>
          </Form.Group>
        </Form.Row>
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    );
  }
}

export default Dashboard;
