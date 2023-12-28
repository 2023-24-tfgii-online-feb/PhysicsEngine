import React from 'react';
import "./SimulationControl.css"; // Create this CSS file for styling

const SimulationControl = ({ stompClient }) => {
    const sendMessage = (endpoint) => {
        stompClient && stompClient.send(endpoint, {}, "");
    };

    return (
        <div className="simulationControl">
            <button onClick={() => sendMessage("/app/random-planet")}>Add Planet</button>
            <button onClick={() => sendMessage("/app/random-asteroid")}>Add Asteroid</button>
            <button onClick={() => sendMessage("/app/random-spaceship")}>Add Spaceship</button>
            <button onClick={() => sendMessage("/app/random-body")}>Add Random Body</button>
        </div>
    );
};

export default SimulationControl;
