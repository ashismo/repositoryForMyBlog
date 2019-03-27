import React, {Component} from 'react';

class EventBind extends Component {
    constructor(props) {
        super(props);
        this.state = {
            message: 'Hello'
        }
    }
    clickHandler() {
        let test=10;
        debugger;
        this.setState({
            message: 'Event Binding!'
        })
        console.log("Event binding example");
    }
    render() {
        return (
            <div>
                <button onClick={this.clickHandler}>Click me - Event Binding</button>
            </div>
        )
    }
}

export default EventBind;