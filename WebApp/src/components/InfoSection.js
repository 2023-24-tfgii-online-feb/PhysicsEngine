import React, { useEffect, useState } from "react";
import "./InfoSection.css";

const InfoSection = ({ stompClient }) => {
  const [bodies, setBodies] = useState([]);
  const [selectedBodyIds, setSelectedBodyIds] = useState([]);

  useEffect(() => {
    if (stompClient) {
      stompClient.subscribe("/topic/bodies", (message) => {
        const receivedBodies = JSON.parse(message.body);
        setBodies(receivedBodies);

        const selectedIds = receivedBodies
          .filter((body) => body.selected)
          .map((body) => body.id);
        setSelectedBodyIds(selectedIds);
      });
    }
  }, [stompClient]);

  const handleRemoveBody = (id, event) => {
    // Prevent click event from bubbling up to the card
    event.stopPropagation();
    stompClient.send("/app/remove-body", {}, JSON.stringify({ id }));
  };

  const handleCardClick = (id) => {
    // Toggle selection state locally
    const newSelectedBodyIds = selectedBodyIds.includes(id)
      ? selectedBodyIds.filter((existingId) => existingId !== id)
      : [...selectedBodyIds, id];
    setSelectedBodyIds(newSelectedBodyIds);

    // Send toggle message to backend
    stompClient.send("/app/toggle-select-body", {}, JSON.stringify({ id }));
  };

  return (
    <div className="InfoSection">
      {bodies.map((body) => (
        <div
          key={body.id}
          className={`card ${
            selectedBodyIds.includes(body.id) ? "selected" : ""
          }`}
          onClick={() => handleCardClick(body.id)}
        >
          <div className="card-body">
            {/* Display body information */}
            <p>
              Body ID: {body.id} - Type: {body.bodyType}
            </p>
            <p>Body Mass: {body.mass}</p>
            <p>
              Body Velocity: 
            </p>
            <p>
            x: {body.velocity.x.toFixed(7)},y: {body.velocity.y.toFixed(7)}
              </p>
            {/* Other information */}
          </div>
          <button
            onClick={(e) => handleRemoveBody(body.id, e)}
            className="delete-button"
          >
            Delete
          </button>
        </div>
      ))}
    </div>
  );
};

export default InfoSection;
