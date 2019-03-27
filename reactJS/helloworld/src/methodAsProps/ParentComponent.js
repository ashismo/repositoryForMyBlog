import React, {Component} from 'react';
import ChildComponent from './ChildComponent';

class ParentComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            parenrName: 'Parent'
        }
    }

    greetParent(childName) {
        alert(`Hello ${this.state.parenrName} from ${childName}`);
    }
    render() {
        return(<div>
            <ChildComponent greetHandler={this.greetParent}/>
        </div>)
    }
}

export default ParentComponent;