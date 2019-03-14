import React from 'react';

const PropsExampleFunction = (props) => {
    return(
        <div>
            <h1>Hello {props.name} {props.surname} - React JS Functional component props</h1>
            {props.children}
        </div>
    )
}

export default PropsExampleFunction;