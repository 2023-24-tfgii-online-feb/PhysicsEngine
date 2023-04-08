document.getElementById('login-form').addEventListener('submit', (event) => {
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
  
    // Perform login authentication and redirect
    // For now, we'll assume a successful login and redirect to index.html
    window.location.href = 'index.html';
  });
  