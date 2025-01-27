import './Recipe.css';
import { Button, Card } from "react-bootstrap";
import React from "react";
import { Link } from 'react-router-dom';

function Recipe({ id, title, description, image }) {
    return (
        <Card style={{ width: '18rem' }}>
            <Card.Img variant="top" src={image} />
            <Card.Body>
                <Card.Title>{title}</Card.Title>
                <p>{description}</p>

                <Link to={`/edit-recipe/${id}`}>
                    <Button variant="primary">Edit Details</Button>
                </Link>
            </Card.Body>
        </Card>
    );
}

export default Recipe;