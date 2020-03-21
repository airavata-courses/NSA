import React from "react";
import { Button, Table } from "reactstrap";
import axios from "axios";


class History extends React.Component{
    constructor(props){
        super(props);
        this.state ={ 
            sessions: [
                {
                    jobstatus: 'loading',
                    jobtype: 'loading',
                    timeStamp: 'loading',
                    userID: 'loading',
                    day: 'loading',
                    month: 'loading',
                    year: 'loading',
                    radarID: 'loading',
                    output: 'loading',
                    createdAt: 'loading',
                },
            ],
            fields: [
                'Job Status',
                'Job Type',
                'User ID',
                'Day',
                'Month',
                'Year',
                'Radar ID',
                'Output',
                'Timestamp',
            ]
        }
        let user = {
            userID: this.props.userID,
            day: "",
            month: "",
            year: "",
            radarID: "",
            output: "",
            jobstatus: "",
            jobtype: "login",
        };
        axios
            .post("http://" + process.env.REACT_APP_BACKEND_IP + ":32450/sessionmgmt", JSON.parse(JSON.stringify(user)))
            .then(res => {
                console.log("session_data", res.data);
                this.setState({sessions: res.data.sessions})

            });
    }

    render() {
        let headings = this.state.fields.map(
            (item, key) => <th>{item}</th>
        );
        let sessions = this.state.sessions.map(
            (item, key) => <SessionItem item={item}/>
        );
        
        return(
            <div>
                <div class='login-form'>
                    History of user: {this.props.userID}
                </div>
                <div>
                    <Table>
                        <tr>
                            {headings}
                        </tr>
                        {sessions}
                    </Table>
                </div>
                <div class='login-form'>
                    <Button onClick={this.props.views.dashboard} className="btn-lg btn-dark btn-block">
                        Dashboard
                    </Button>
                </div>
            </div>
        );
    }
}

class SessionItem extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.item.jobstatus}</td>
                <td>{this.props.item.jobtype}</td>
                <td>{this.props.item.userID}</td>
                <td>{this.props.item.day}</td>
                <td>{this.props.item.month}</td>
                <td>{this.props.item.year}</td>
                <td>{this.props.item.radarID}</td>
                <td>{this.props.item.output}</td>
                <td>{this.props.item.createdAt}</td>
            </tr>
        );
    }
}

export default History;
