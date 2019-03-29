import React from 'react';

function ListRendering() {
    const names = ['Ashish', 'Dona', 'Ujan'];

    return (
        <div> Using Javascipt List: 
            {
                names.map(name => <h2>{name}</h2>)
            }
            Using index of the list
            <h2>{names[0]}</h2>
            <h2>{names[1]}</h2>
            <h2>{names[2]}</h2>
        </div>
    )
}

export default ListRendering;