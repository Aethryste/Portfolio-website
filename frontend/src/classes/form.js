class Form {
  constructor(fromEmail, fromName, body) {
    this.senderAddress = fromEmail;
    this.senderName = fromName;
    this.content = body;
  }
}
export default Form;
