# EtonDigital

# Test Plan

## Objective
To thoroughly test the login process of the Storabble platform to ensure that users can successfully log in with valid credentials and appropriate error messages are displayed for invalid credentials.

## Scope
- Functional Testing
- User Experience Testing
- Security Testing

## Test Approach
- Manual Testing
- Automated Testing using Java, Selenium, TestNG(running and taking screenshoots) and Allure for reporting

## Testing Methodologies
- **Functional Testing**: Verify that the login functionality works as expected.
- **Boundary Testing**: Check the system's response to input at the edge of allowable values.
- **Negative Testing**: Ensure the application handles invalid inputs gracefully.
- **User Experience Testing**: Validate the ease of use and the interface consistency.
- **Security Testing**: Test for vulnerabilities like SQL injection, cross-site scripting (XSS), etc.

## Test Scenarios
1. Verify the login with valid email and password.
2. Verify the login with an invalid email.
3. Verify the login with an invalid password.
4. Verify the login with both email and password as invalid.
5. Verify the error message when fields are left empty.
6. Verify the redirection to 'My storage listings' page upon successful login.
7. Verify that the login form is not submitted if fields are not populated.
8. Verify the error message format and style.
9. Test for SQL Injection attempts in email and password fields.
10. Test for XSS vulnerabilities in email and password fields.
11. Verify that the application is protected against CSRF attacks.
12. Verify that the application protects against brute force login attempts.
13. Verify that sensitive data is not exposed in error messages or URL parameters.
14. Verify that cookies are marked as secure and HttpOnly.

## Test Cases

### **Test Case 1: Valid Login**
- **Description:** Verify login with valid email and password.
- **Preconditions:** User has a registered account.
- **Steps:**
  1. Navigate to the login page.
  2. Enter valid email.
  3. Enter valid password.
  4. Click on the login button.
- **Expected Result:** User is redirected to Home page.

### **Test Case 2: Invalid Email**
- **Description:** Verify login with an invalid email.
- **Preconditions:** None.
- **Steps:**
  1. Navigate to the login page.
  2. Enter invalid email.
  3. Enter valid password.
  4. Click on the login button.
- **Expected Result:** Error message "Please enter a valid email address." displayed, user remains on the login page.

### **Test Case 3: Invalid Password**
- **Description:** Verify login with an invalid password.
- **Preconditions:** User has a registered account.
- **Steps:**
  1. Navigate to the login page.
  2. Enter valid email.
  3. Enter invalid password.
  4. Click on the login button.
- **Expected Result:** Error message "Your password is incorrect." displayed, user remains on the login page.

### **Test Case 4: Both Email and Password Invalid**
- **Description:** Verify login with both email and password as invalid.
- **Preconditions:** None.
- **Steps:**
  1. Navigate to the login page.
  2. Enter invalid email.
  3. Enter invalid password.
  4. Click on the login button.
- **Expected Result:** Error messages displayed, user remains on the login page.

### **Test Case 5: Empty Fields**
- **Description:** Verify login with empty email and password fields.
- **Preconditions:** None.
- **Steps:**
  1. Navigate to the login page.
  2. Leave email field empty.
  3. Leave password field empty.
  4. Click on the login button.
- **Expected Result:** Error messages "This field is required." displayed, user remains on the login page.

### **Test Case 6: Only Email Populated**
- **Description:** Verify login with only email field populated.
- **Preconditions:** None.
- **Steps:**
  1. Navigate to the login page.
  2. Enter valid email.
  3. Leave password field empty.
  4. Click on the login button.
- **Expected Result:** Error message "This field is required." displayed, user remains on the login page.

### **Test Case 7: Only Password Populated**
- **Description:** Verify login with only password field populated.
- **Preconditions:** None.
- **Steps:**
  1. Navigate to the login page.
  2. Leave email field empty.
  3. Enter valid password.
  4. Click on the login button.
- **Expected Result:** Error message "This field is required." displayed, user remains on the login page.

### **Test Case 8: Error Message Format**
- **Description:** Verify the error message format and style.
- **Preconditions:** None.
- **Steps:**
  1. Navigate to the login page.
  2. Enter invalid email.
  3. Enter invalid password.
  4. Click on the login button.
- **Expected Result:** Error message should be displayed in a consistent format as per the design guidelines.

### **Test Case 9: SQL Injection**
- **Description:** Verify login with SQL injection attempts.
- **Preconditions:** None.
- **Steps:**
  1. Navigate to the login page.
  2. Enter SQL injection script in email field.
  3. Enter SQL injection script in password field.
  4. Click on the login button.
- **Expected Result:** Error message displayed, user remains on the login page. No SQL queries are executed.

### **Test Case 10: XSS Vulnerability**
- **Description:** Verify login fields for XSS vulnerabilities.
- **Preconditions:** None.
- **Steps:**
  1. Navigate to the login page.
  2. Enter XSS script in email field.
  3. Enter XSS script in password field.
  4. Click on the login button.
- **Expected Result:** Error message displayed, user remains on the login page. No scripts are executed.

### **Test Case 11: CSRF (Cross-Site Request Forgery) Protection**
- **Description:** Verify that the application is protected against CSRF attacks.
- **Preconditions:** None.
- **Steps:**
  1. Navigate to the login page.
  2. Check if a CSRF token is present in the login form.
  3. Attempt to submit the login form without the CSRF token.
- **Expected Result:** The form submission should fail, and an error message should be displayed.

### **Test Case 12: Brute Force Protection**
- **Description:** Verify that the application protects against brute force login attempts.
- **Preconditions:** None.
- **Steps:**
  1. Navigate to the login page.
  2. Attempt to login with incorrect credentials multiple times in quick succession.
- **Expected Result:** The application should lock the account or display a captcha after a certain number of failed attempts.

### **Test Case 13: Sensitive Data Exposure**
- **Description:** Verify that sensitive data is not exposed in error messages or URL parameters.
- **Preconditions:** None.
- **Steps:**
  1. Navigate to the login page.
  2. Attempt to login with invalid credentials.
  3. Check the error message and URL parameters.
- **Expected Result:** Error messages and URL parameters should not expose sensitive data such as email addresses or passwords.

### **Test Case 14: Secure Cookies**
- **Description:** Verify that cookies are marked as secure and HttpOnly.
- **Preconditions:** None.
- **Steps:**
  1. Navigate to the login page.
  2. Login with valid credentials.
  3. Check the cookies for secure and HttpOnly attributes.
- **Expected Result:** Cookies should be marked as secure and HttpOnly.


### Bugs Found:

#### **Bug ID:** 001
- **Severity:** High
- **Description:** HomePage does not load after successful login. The page remains light blue with no elements displayed.
- **Steps to Reproduce:**
  1. Navigate to the login page.
  2. Enter valid email and password.
  3. Click on the login button.
- **Expected Result:** User is redirected to ‘My storage listings’ page with all elements properly loaded.
- **Actual Result:** User is redirected to a light blue page with no elements displayed.
- **Note** Found with automation, if I delete "?page=1" from URL and click enter, page loads successfully

#### **Bug ID:** 002
- **Severity:** Medium
- **Description:** Incorrect error message when invalid email and correct password are entered. The error message incorrectly states that the password is incorrect.
- **Steps to Reproduce:**
  1. Navigate to the login page.
  2. Enter invalid email.
  3. Enter correct password.
  4. Click on the login button.
- **Expected Result:** Error message indicating that the email is incorrect.
- **Actual Result:** Error message incorrectly indicates that the password is also incorrect.

#### **Bug ID:** 003
- **Severity:** Medium
- **Description:** Incorrect error message when invalid password and correct email are entered. The error message incorrectly states that the email is incorrect.
- **Steps to Reproduce:**
  1. Navigate to the login page.
  2. Enter correct email.
  3. Enter invalid password.
  4. Click on the login button.
- **Expected Result:** Error message indicating that the password is incorrect.
- **Actual Result:** Error message incorrectly indicates that the email is also incorrect.

#### **Bug ID:** 004
- **Severity:** Critical
- **Description:** Password is transmitted in plaintext within the request payload.
- **Steps to Reproduce:**
  1. Navigate to the login page.
  2. Open developer tools and go to the Network tab.
  3. Enter a valid email and password.
  4. Click on the login button and inspect the request payload.
- **Expected Result:** Password should be hashed or encrypted before being sent over the network.
- **Actual Result:** Password is sent in plaintext.
- **Recommendation:** Implement client-side hashing or encryption before sending the password to the server.



#### **NOTE**
- Not sure for bug 2 and 3, maybe it is by design. Keeping in mind GDPR regulations, it might be that we are hidding which element specificaly is incorect therefore both messages are visible