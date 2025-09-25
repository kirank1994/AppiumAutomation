package API;
//"company": {
//"name": "Romaguera-Crona",
//"catchPhrase": "Multi-layered client-server neural-net",
//"bs": "harness real-time e-markets"
//}
public class Company {
private String name;
private String catchPhrase;
private String bs;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCatchPhrase() {
	return catchPhrase;
}
public void setCatchPhrase(String catchPhrase) {
	this.catchPhrase = catchPhrase;
}
public String getBs() {
	return bs;
}
public void setBs(String bs) {
	this.bs = bs;
}
public Company() {}
}