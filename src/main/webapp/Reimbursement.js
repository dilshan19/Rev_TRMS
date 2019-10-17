class Reimbursement {
    constructor(name, type, manufacturer, cost, owner, difficulty) {
        this.name = name;
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = cost;
        this.difficulty = difficulty;
        this.owner = {"username":owner};
    }
}