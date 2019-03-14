import React from 'react';

const FunctionClick = (props) => {
    const clickme = () => {
        console.log("Button clicked - Functional Component");
    }
    return(
        <div>
            <button onClick={clickme}>Click me - Functional Component</button>
        </div>
    )
}

export default FunctionClick;