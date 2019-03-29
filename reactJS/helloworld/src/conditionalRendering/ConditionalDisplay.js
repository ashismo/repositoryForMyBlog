import React, {Component} from 'react';

class ConditionlDisplay extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoggedIn: true
        }
    }
    render() {

        return this.state.isLoggedIn ? 
            (<div>Conditional Rendering 4 Approaches - Welcome Ashish</div>) :
            (<div>Conditional Rendering 4 Approaches  - Welcome Guest</div>);

        /*let message;
        if(this.state.isLoggedIn) {
            message = "Conditional Rendering 4 Approaches  - Welcome Ashish";
        } else {
            message = "Conditional Rendering 4 Approaches  - Welcome Guest";
        }
        return <div>{message}</div>*/

        /*if(this.state.isLoggedIn) {
            return (
                <div>Conditional Rendering 4 Approaches  - Welcome Ashish</div>
            )
        } else {
            return (
                <div>Conditional Rendering 4 Approaches  - Welcome Guest</div>
            )
        }*/
        /*return (
            <div>
                <div>Welcome Ashish</div>
                <div>Welcome Guest</div>
            </div>
        )*/
    }
}

export default ConditionlDisplay;