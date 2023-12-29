import React, { useState, useEffect } from "react";
import "./App.css";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Canvas from "./components/Canvas";
import InfoSection from "./components/InfoSection";
import SimulationControl from "./components/SimulationControl";
import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";

function App() {
  const [stompClient, setStompClient] = useState(null);

  useEffect(() => {
    const socket = new SockJS("http://localhost:3100/physics-engine-websocket");
    const client = Stomp.over(socket);

    client.connect({}, () => {
      setStompClient(client);
    });

    return () => {
      if (client) {
        client.disconnect();
      }
    };
  }, []);

  return (
    <div className="App">
      <Header />
      <div id="content">
        <div className="simulationContainer">
          <Canvas stompClient={stompClient} />
          <SimulationControl stompClient={stompClient} />
        </div>
        <InfoSection stompClient={stompClient} />
      </div>
      <Footer />
    </div>
  );
}

export default App;
