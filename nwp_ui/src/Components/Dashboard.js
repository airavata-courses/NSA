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
            dataretrieval_information: {
                userName: null,
                month: null,
                year: null,
                radarID: null,
                day: null,
            },
            radarID: {value: 'KIND', label: 'KIND'},
            options: [
                {value: 'DAN1', label: 'DAN1'},
                {value: 'KABR', label: 'KABR'},
                {value: 'KABX', label: 'KABX'},
                {value: 'KAKQ', label: 'KAKQ'},
                {value: 'KAMA', label: 'KAMA'},
                {value: 'KAMX', label: 'KAMX'},
                {value: 'KAPX', label: 'KAPX'},
                {value: 'KARX', label: 'KARX'},
                {value: 'KATX', label: 'KATX'},
                {value: 'KBBX', label: 'KBBX'},
                {value: 'KBGM', label: 'KBGM'},
                {value: 'KBHX', label: 'KBHX'},
                {value: 'KBIS', label: 'KBIS'},
                {value: 'KBLX', label: 'KBLX'},
                {value: 'KBMX', label: 'KBMX'},
                {value: 'KBOX', label: 'KBOX'},
                {value: 'KBRO', label: 'KBRO'},
                {value: 'KBUF', label: 'KBUF'},
                {value: 'KBYX', label: 'KBYX'},
                {value: 'KCAE', label: 'KCAE'},
                {value: 'KCBW', label: 'KCBW'},
                {value: 'KCBX', label: 'KCBX'},
                {value: 'KCCX', label: 'KCCX'},
                {value: 'KCLE', label: 'KCLE'},
                {value: 'KCLX', label: 'KCLX'},
                {value: 'KCRP', label: 'KCRP'},
                {value: 'KCXX', label: 'KCXX'},
                {value: 'KCYS', label: 'KCYS'},
                {value: 'KDAX', label: 'KDAX'},
                {value: 'KDDC', label: 'KDDC'},
                {value: 'KDFX', label: 'KDFX'},
                {value: 'KDGX', label: 'KDGX'},
                {value: 'KDLH', label: 'KDLH'},
                {value: 'KDMX', label: 'KDMX'},
                {value: 'KDOX', label: 'KDOX'},
                {value: 'KDTX', label: 'KDTX'},
                {value: 'KDVN', label: 'KDVN'},
                {value: 'KEAX', label: 'KEAX'},
                {value: 'KEMX', label: 'KEMX'},
                {value: 'KENX', label: 'KENX'},
                {value: 'KEOX', label: 'KEOX'},
                {value: 'KEPZ', label: 'KEPZ'},
                {value: 'KESX', label: 'KESX'},
                {value: 'KEVX', label: 'KEVX'},
                {value: 'KEWX', label: 'KEWX'},
                {value: 'KEYX', label: 'KEYX'},
                {value: 'KFCX', label: 'KFCX'},
                {value: 'KFDR', label: 'KFDR'},
                {value: 'KFFC', label: 'KFFC'},
                {value: 'KFSD', label: 'KFSD'},
                {value: 'KFSX', label: 'KFSX'},
                {value: 'KFTG', label: 'KFTG'},
                {value: 'KFWS', label: 'KFWS'},
                {value: 'KGGW', label: 'KGGW'},
                {value: 'KGJX', label: 'KGJX'},
                {value: 'KGLD', label: 'KGLD'},
                {value: 'KGRB', label: 'KGRB'},
                {value: 'KGRK', label: 'KGRK'},
                {value: 'KGRR', label: 'KGRR'},
                {value: 'KGSP', label: 'KGSP'},
                {value: 'KGWX', label: 'KGWX'},
                {value: 'KGYX', label: 'KGYX'},
                {value: 'KHDX', label: 'KHDX'},
                {value: 'KHGX', label: 'KHGX'},
                {value: 'KHNX', label: 'KHNX'},
                {value: 'KHPX', label: 'KHPX'},
                {value: 'KHTX', label: 'KHTX'},
                {value: 'KICT', label: 'KICT'},
                {value: 'KICX', label: 'KICX'},
                {value: 'KILN', label: 'KILN'},
                {value: 'KILX', label: 'KILX'},
                {value: 'KIND', label: 'KIND'},
                {value: 'KINX', label: 'KINX'},
                {value: 'KIWA', label: 'KIWA'},
                {value: 'KIWX', label: 'KIWX'},
                {value: 'KJAX', label: 'KJAX'},
                {value: 'KJGX', label: 'KJGX'},
                {value: 'KJKL', label: 'KJKL'},
                {value: 'KLBB', label: 'KLBB'},
                {value: 'KLCH', label: 'KLCH'},
                {value: 'KLGX', label: 'KLGX'},
                {value: 'KLIX', label: 'KLIX'},
                {value: 'KLNX', label: 'KLNX'},
                {value: 'KLOT', label: 'KLOT'},
                {value: 'KLRX', label: 'KLRX'},
                {value: 'KLSX', label: 'KLSX'},
                {value: 'KLTX', label: 'KLTX'},
                {value: 'KLVX', label: 'KLVX'},
                {value: 'KLWX', label: 'KLWX'},
                {value: 'KLZK', label: 'KLZK'},
                {value: 'KMAF', label: 'KMAF'},
                {value: 'KMAX', label: 'KMAX'},
                {value: 'KMBX', label: 'KMBX'},
                {value: 'KMHX', label: 'KMHX'},
                {value: 'KMKX', label: 'KMKX'},
                {value: 'KMLB', label: 'KMLB'},
                {value: 'KMOB', label: 'KMOB'},
                {value: 'KMPX', label: 'KMPX'},
                {value: 'KMQT', label: 'KMQT'},
                {value: 'KMRX', label: 'KMRX'},
                {value: 'KMSX', label: 'KMSX'},
                {value: 'KMTX', label: 'KMTX'},
                {value: 'KMUX', label: 'KMUX'},
                {value: 'KMVX', label: 'KMVX'},
                {value: 'KMXX', label: 'KMXX'},
                {value: 'KNKX', label: 'KNKX'},
                {value: 'KNQA', label: 'KNQA'},
                {value: 'KOAX', label: 'KOAX'},
                {value: 'KOHX', label: 'KOHX'},
                {value: 'KOKX', label: 'KOKX'},
                {value: 'KOTX', label: 'KOTX'},
                {value: 'KPAH', label: 'KPAH'},
                {value: 'KPBZ', label: 'KPBZ'},
                {value: 'KPDT', label: 'KPDT'},
                {value: 'KPOE', label: 'KPOE'},
                {value: 'KPUX', label: 'KPUX'},
                {value: 'KRAX', label: 'KRAX'},
                {value: 'KRGX', label: 'KRGX'},
                {value: 'KRIW', label: 'KRIW'},
                {value: 'KRLX', label: 'KRLX'},
                {value: 'KRTX', label: 'KRTX'},
                {value: 'KSFX', label: 'KSFX'},
                {value: 'KSGF', label: 'KSGF'},
                {value: 'KSHV', label: 'KSHV'},
                {value: 'KSJT', label: 'KSJT'},
                {value: 'KSOX', label: 'KSOX'},
                {value: 'KSRX', label: 'KSRX'},
                {value: 'KTBW', label: 'KTBW'},
                {value: 'KTFX', label: 'KTFX'},
                {value: 'KTLH', label: 'KTLH'},
                {value: 'KTLX', label: 'KTLX'},
                {value: 'KTWX', label: 'KTWX'},
                {value: 'KTYX', label: 'KTYX'},
                {value: 'KUDX', label: 'KUDX'},
                {value: 'KUEX', label: 'KUEX'},
                {value: 'KVNX', label: 'KVNX'},
                {value: 'KVTX', label: 'KVTX'},
                {value: 'KVWX', label: 'KVWX'},
                {value: 'KYUX', label: 'KYUX'},
                {value: 'PHKI', label: 'PHKI'},
                {value: 'PHKM', label: 'PHKM'},
                {value: 'PHMO', label: 'PHMO'},
                {value: 'PHWA', label: 'PHWA'},
                {value: 'TJUA', label: 'TJUA'}
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
        this.setState(
            {dataretrieval_information:{
                userName: this.props.userName,
                day,
                radarID,
                month,
                year
            }
            }, console.log(this.state.dataretrieval_information))
        // let message = [day + ' ' + month + ' ' + year + ' ' + radarID + ' ' + this.state.user.userName];
        

        let message = JSON.stringify(this.state.dataretrieval_information);
        message = JSON.parse(message);
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
