import React from 'react';

const WithJsx = () => {
    return React.createElement('div',
             {id: 'hello', className: 'dummyClass'}, 
             React.createElement('h1', null, 'Hello Ashish - With JSX'));
}

export default WithJsx;