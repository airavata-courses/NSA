import React from "react";

import "./App.css";
import { Button, Form, FormGroup, Label, Input } from "reactstrap";
import { Link } from "react-router";
import { BrowserRouter as Router, Route } from "react-router-dom";
import { FacebookLoginButton } from "react-social-login-buttons";
import Login from "./Components/Login";
import Register from "./Components/Register";
import Dashboard from "./Components/Dashboard";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      curr_view: props => (
        <Dashboard {...props} changeView={this.changeToDashboard} />
      )
    };
  }
  changeToDashboard = () => {
    this.setState({
      curr_view: props => <Dashboard {...props} />
    });
  };

  // changeToLogin() {
  //   this.setState({
  //     curr_view: <Login changeToDashboard={this.changeToDashboard} />
  //   });
  // }

  render() {
    return (
      // <Form className="landing-page">
      //   <h1>Numerical Weather Prediction Portal</h1>
      //   <FormGroup>
      //     <Button className="btn-lg btn-dark btn-block" onClick={Login}>
      //       <Route path="/" component={Login} />
      //     </Button>
      //   </FormGroup>
      //   <Button className="btn-lg btn-dark btn-block">Register</Button>
      // </Form>
      <Router>
        <div>
          {/* <Route path="/" component={this.state.curr_view} exact /> */}
          <Route path="/" render={this.state.curr_view} />
        </div>
      </Router>
    );
  }
}

export default App;
