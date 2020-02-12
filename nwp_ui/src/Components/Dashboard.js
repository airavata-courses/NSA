import React from "react";

// import "../App.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import {Button, Form} from "reactstrap";
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
            options : [
                {value: 'KIND', label: 'KIND'},
                {value: 'KINX', label: 'KINX'},
            ]
        }

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


    wrapData = () => {
        let dateISO = this.state.date.toISOString();
        let year = dateISO.substring(0,4);
        let month = dateISO.substring(5,7);
        let day = dateISO.substring(8, 10);
        let radarID = this.state.radarID.value;
        let message = [day + ' ' + month + ' ' + year + ' ' + radarID + ' ' + this.state.user.userName];
        console.log(message)
        axios.post("http://localhost:8081/dataretrieval", message).then(res=>{
            console.log(res.data);
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
             <Button className="btn-lg btn-dark btn-block" onClick={this.props.changeToLogin}>
                 Back to Login
             </Button>

     </Form>
    );
  }
}

export default Dashboard;
