import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import CustomGreet from './components/FunctionalComponent';
import ClassComponent from './components/ClassComponent';
import WithoutJsx from './jsx/WithoutJsx';
import WithJsx from './jsx/WithJsx';
import PropsExampleFunction from './props/PropsExampleFunction';
import PropsExampleClassComponent from './props/PropsExampleClassComponent';
import DestructuringFunction from './destructuring/DestructuringFunction';
import DestructuringClass from './destructuring/DestructuringClass';
import FunctionClick from './eventhandling/FunctionClick';
import ClassClick from './eventhandling/ClassClick';

class App extends Component {
  render() {
    return (
      <div className="App">
        <FunctionClick/>
        <ClassClick/>
        <CustomGreet/>
        <ClassComponent/>
        <WithoutJsx/>
        <WithJsx/>
        <PropsExampleFunction name='Ashish' surname='Mondal'>
          <p>Functional component - Props Chidren tag</p>
        </PropsExampleFunction>
        <PropsExampleFunction name='Dona' surname='Ghosh'/>

        <PropsExampleClassComponent name='Ashish' surname='Mondal'>
          <p>Class component - Props Chidren tag</p>
        </PropsExampleClassComponent>
        <PropsExampleClassComponent name='Dona' surname='Ghosh'/>

        <DestructuringFunction name='Ashish' surname='Mondal'>
          <p>Destructuring Functional component - Props Chidren tag</p>
        </DestructuringFunction>

        <DestructuringClass name='Ashish' surname='Mondal'>
          <p>Distructuring Class component - Props Chidren tag</p>
        </DestructuringClass>
      </div>

      
    );
  }
}

export default App;
