import React from "react";
import { Button } from "reactstrap";

class History extends React.Component{
    constructor(props){
        super(props);
    }
    render() {
        return(
            <div className='login-form'>
                <div>put image here</div>
                <Button onClick={this.props.views.dashboard} className="btn-lg btn-dark btn-block">
                    Dashboard
                </Button>
            </div>
        );
    }
}

export default History;
