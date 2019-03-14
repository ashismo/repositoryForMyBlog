import React, {Component} from 'react';

class PropsExampleClassComponent extends Component {
    render() {
        return(<div>
            <h1>Hello {this.props.name} {this.props.surname} - React JS Class level props</h1>
            {this.props.children}
        </div>)
    }
}

export default PropsExampleClassComponent;