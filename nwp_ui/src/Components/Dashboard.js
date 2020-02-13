import React, {useState} from "react";
// import "../App.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import {Button, Form, Alert} from "reactstrap";
import Select from "react-select";
import axios from "axios";

class Dashboard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            date: new Date(),
            user: {
                userName: "sj",
                firstName: "shiv",
                lastName: "Jejurkar",
                emailId: "null"
            },
            radarID: 'KIND',
            options: [
                {value: 'KIND', label: 'KIND'},
                {value: 'KINX', label: 'KINX'},
            ],
            image: null,
            alerts: null,
        };

    };

    FutureDateAlert = (props) => {
        // const [visible, setVisible] = useState(true);
        // const onDismiss = () => setVisible(false);

        return (
            <Alert color="danger">

                Please select a date in the past.
            </Alert>
        );
    }
    // handleChange = event => {
    //   this.setState({ name: event.target.value });
    // };

    handleDateChange = date => {
        this.setState({date},
            () => console.log(`Date selected:`, this.state.date)
        );
    };

    handleRadarChange = radarID => {
        this.setState({radarID},
            () => console.log(`Radar selected:`, this.state.radarID)
        );
    }

    displayImage = () => {
        this.setState({
            image: <img src={require('../1.png')}/>
        //     image:
        // <div>lol</div>
    });
    }


    wrapData = () => {
        let yesterday = new Date();
        yesterday.setDate(yesterday.getDate() - 1);
        let difference = this.state.date.getTime() - yesterday.getTime();
        if(difference > 86400){
            this.setState({alerts: this.FutureDateAlert()});
            return;
        }
        let dateISO = this.state.date.toISOString();
        let year = dateISO.substring(0,4);
        let month = dateISO.substring(5,7);
        let day = dateISO.substring(8, 10);
        let radarID = this.state.radarID.value;
        let message = [day + ' ' + month + ' ' + year + ' ' + radarID + ' ' + this.state.user.userName];
        console.log(message)
        axios.post("http://localhost:8081/dataretrieval", message).then(res=>{
            console.log("res.data",res.data);
            if(res.data == 'success'){
                this.displayImage();
            }
        });
    };


    // const jsonobj = JSON.stringify(user);
    // const Col = 1;

    // axios.post("http://localhost:8081/login", { user }).then(res => {
    //   console.log(res.data);
    //   if (res.data) {
    //     <div>
    //       <Link to="/Register"> </Link>;
    //     </div>;
    //   }
    // });

    render() {
        return (
            <Form className="dashboard-form">
                <div>
                    {this.state.alerts}
                </div>
                Welcome to the dashboard
                <div>
                    Date:
                    <DatePicker
                        selected={this.state.date}
                        onChange={this.handleDateChange}
                    />
                </div>
                <div>
                    RadarID:
                    <Select
                        value={this.state.radarID}
                        onChange={this.handleRadarChange}
                        options={this.state.options}
                    />
                </div>
                <Button className="btn-lg btn-dark btn-block" onClick={this.wrapData}>
                    Forecast!
                </Button>
                <Button className="btn-lg btn-dark btn-block" onClick={this.props.views.history}>
                    History
                </Button>
                <Button className="btn-lg btn-dark btn-block" onClick={this.props.views.login}>
                    Back to Login
                </Button>
                <div>
                    {this.state.image}
                </div>
            </Form>
        );
    }
}

export default Dashboard;
