import React from "react";
import { Button } from "reactstrap";
import axios from "axios";

class History extends React.Component{
    constructor(props){
        super(props);
        let sessionmgmt = {
            userName: this.props.userName,
            day: null,
            month: null,
            year: null,
            radarID: null,
            output: null,
            jobstatus: null,
            jobtype: null
        }
        let message = JSON.stringify(sessionmgmt);
        message = JSON.parse(message);
        axios.post("http://localhost:8081/sessionmgmt", message).then(res => {
            console.log("res.data", res.data);
        });
    }
    render() {
        return(
            <div className='login-form'>
                <div>put image here</div>
                <Button onClick={this.props.views.dashboard(this.props.userName)} className="btn-lg btn-dark btn-block">
                    Dashboard
                </Button>
            </div>
        );
    }
}

export default History;
