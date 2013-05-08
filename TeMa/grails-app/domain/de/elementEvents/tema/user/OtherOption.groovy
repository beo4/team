package de.elementEvents.tema.user

import java.util.Formatter.DateTime;


class OtherOption {

    String vegatarian
    Boolean vegan
    String allergy
    String wishes
    
    static belongsTo = [user:User]
    
    
    static mapping = {
        wishes type: 'text'
        allergy type: 'text'
    }
    
    static constraints = {
        vegatarian blank: true, nullable: true
        vegan blank: true, nullable: true
        allergy blank: true, nullable: true
        wishes blank: true, nullable: true
    }
}

enum Vegetarian {
    NOT("NOT"), FISH("Fish"), FULL("Full")
    Vegetarian(String value) { this.value = value }
    final String value
    String getKey() { name() }
    String toString() { value }
    
}
