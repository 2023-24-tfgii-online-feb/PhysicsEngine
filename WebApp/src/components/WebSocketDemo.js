import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

const WebSocketDemo = () => {
  const [message, setMessage] = useState(''); // State to store the received message

  useEffect(() => {
    // Connect to the WebSocket
    const socket = new SockJS('http://localhost:3100/physics-engine-websocket');
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, frame => {
      console.log('Connected: ' + frame);

      // Subscribe to a topic
      stompClient.subscribe('/topic/bodies', message => {
        console.log(message.body);
        setMessage(message.body); // Set the received message
      });
    });

    return () => {
      // Disconnect on cleanup
      if (stompClient) {
        stompClient.disconnect();
      }
    };
  }, []);

  // Function to request data from the server
  const requestData = () => {
    // Assuming stompClient is available globally or in the appropriate scope
    const socket = new SockJS('http://localhost:3100/physics-engine-websocket');
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
      // Send a message to a specific topic
      stompClient.send("/app/retrieve-bodies", {}, JSON.stringify({ request: "requestData" }));
    });
  };

  return (
    <div>
      <h2>WebSocket Demo</h2>
      <button onClick={requestData}>Request Data</button>
      <p>Response: {message}</p>
    </div>
  );
};

export default WebSocketDemo;
