import React from "react";
import { Button, Table } from "reactstrap";

class History extends React.Component{
    constructor(props){
        super(props);
        this.state ={ 
            sessions: [
                {
                    jobstatus: '',
                    jobtype: 'login',
                    timeStamp: `2020-03 - 11T21: 43: 08.441Z`,
                    userID: 'shivali',
                    day: '',
                    month: '',
                    year: '',
                    radarID: '',
                    output: '',
                    createdAt: `2020 - 03 - 11T21: 46: 14.959Z`,
                    updatedAt: `2020 - 03 - 11T21: 46: 14.959Z`,
                },
                {
                    jobstatus: 'success',
                    jobtype: 'login',
                    userID: 'shivali',
                    day: '',
                    month: '',
                    year: '',
                    radarID: '',
                    output: '',
                    createdAt: `2020 - 03 - 20T00: 50: 58.516Z`,
                    updatedAt: `2020 - 03 - 20T00: 50: 58.516Z`,
                },
                {
                    jobstatus: 'success',
                    jobtype: 'login',
                    userID: 'shivali',
                    day: '',
                    month: '',
                    year: '',
                    radarID: '',
                    output: '',
                    createdAt: `2020 - 03 - 20T05: 50: 20.491Z`,
                    updatedAt: `2020 - 03 - 20T05: 50: 20.491Z`,
                }
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
