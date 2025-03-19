ALTER TABLE category
    ADD number_of_items INT NULL;

ALTER TABLE category
    MODIFY number_of_items INT NOT NULL;