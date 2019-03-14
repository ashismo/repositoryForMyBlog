import React, {Component} from 'react';

class ClassClick extends Component {
    render() {
        const clickme = () => {
            console.log("Button clicked - Class component");
        }
        return (
            <div>
                <button onClick={clickme}>Click me - Class Component</button>
            </div>
        )
    }
}

export default ClassClick;