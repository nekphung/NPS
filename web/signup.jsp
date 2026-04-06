<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Signup</title>

    <style>
        @font-face{
            font-family: 'Roboto';
            src: url('Roboto/Roboto-Regular.ttf');
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Roboto', sans-serif;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: #1f293a;
        }

        .signup-box {
            width: 400px;
        }

        .signup-box form {
            padding: 0 50px;
        }

        h2 {
            font-size: 2em;
            color: #0ef;
            text-align: center;
            margin-bottom: 20px;
        }

        .input-box {
            position: relative;
            margin: 20px 0;
        }

        .input-box input {
            width: 100%;
            height: 50px;
            background: transparent;
            border: 2px solid #2c4766;
            outline: none;
            border-radius: 40px;
            font-size: 1em;
            color: #fff;
            padding: 0 20px;
        }

        .input-box input:focus,
        .input-box input:valid {
            border-color: #0ef;
        }

        .input-box label {
            position: absolute;
            top: 50%;
            left: 20px;
            transform: translateY(-50%);
            color: #fff;
            transition: 0.5s;
            pointer-events: none;
        }

        .input-box input:focus ~ label,
        .input-box input:valid ~ label {
            top: 0;
            font-size: 0.8em;
            background: #1f293a;
            padding: 0 5px;
            color: #0ef;
        }

        .btn {
            width: 100%;
            height: 45px;
            background: #0ef;
            border: none;
            border-radius: 40px;
            cursor: pointer;
            font-size: 1em;
            font-weight: 600;
            color: #1f293a;
            margin-top: 10px;
        }

        .btn:hover {
            background: #2c4766;
            color: #fff;
        }

        .login-link {
            text-align: center;
            margin-top: 15px;
        }

        .login-link a {
            color: #0ef;
            text-decoration: none;
        }

        .login-link a:hover {
            text-decoration: underline;
        }

    </style>
</head>

<body>

<div class="signup-box">
    <form action="signup" method="post">
        <h2>Sign Up</h2>

        <div class="input-box">
            <input type="email" name="email" required>
            <label>Email</label>
        </div>

        <div class="input-box">
            <input type="text" name="username" required>
            <label>Username</label>
        </div>

        <div class="input-box">
            <input type="password" name="password" required>
            <label>Password</label>
        </div>

        <div class="input-box">
            <input type="text" name="firstname">
            <label>First Name</label>
        </div>

        <div class="input-box">
            <input type="text" name="lastname">
            <label>Last Name</label>
        </div>

        <div class="input-box">
            <input type="date" name="dateofbirth">
            <label>Date of Birth</label>
        </div>

        <button type="submit" class="btn">Sign Up</button>

        <div class="login-link">
            <a href="login">Already have an account?</a>
        </div>
    </form>
</div>

</body>
</html>