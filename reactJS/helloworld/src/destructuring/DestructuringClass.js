import React, {Component} from 'react';

class DestructuringClass extends Component {
    render() {
        const {name, surname, children} = this.props;
        return(<div>
            <h1>Hello {name} {surname} - React JS Destructuring Class level props</h1>
            {children}
        </div>)
    }
}

export default DestructuringClass;