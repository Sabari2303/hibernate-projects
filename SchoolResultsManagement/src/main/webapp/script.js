/**
 * 
 */
document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const userType = urlParams.get('type');
    document.getElementById('user-type').value = userType;
    document.getElementById('login-title').innerText = userType === 'admin' ? 'Admin Login' : 'Student Login';
});