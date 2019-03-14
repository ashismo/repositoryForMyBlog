import React from 'react';

const DestructuringFunction = ({name, surname,children}) => {
    return(
        <div>
            <h1>Hello {name} {surname} - React JS Destructuring Functional component props</h1>
            {children}
        </div>
    )
}

export default DestructuringFunction;