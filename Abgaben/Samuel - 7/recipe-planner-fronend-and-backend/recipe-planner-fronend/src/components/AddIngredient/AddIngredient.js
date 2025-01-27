import React from 'react';
import PropTypes from 'prop-types';
import './AddIngredient.css';
import {Button, Col, Form, Row} from "react-bootstrap";

const AddIngredient = ({ingredients, ingredient, updateIngredient, removeIngredient}) => {

    const onInputChange = (e) => {
        const { name, value } = e.target;
        updateIngredient({ ...ingredient, [name]: value });
    };

    return (
        <Row>
            <Col>
                <Form.Group className="mb-1" controlId="formBasicName">
                    <Form.Control placeholder="Name" name="name" value={ingredient.name} onChange={onInputChange}/>
                </Form.Group>
            </Col>
            <Col>
                <Form.Group className="mb-1" controlId="formBasicUnit">
                    <Form.Select name="unit" value={ingredient.unit} onChange={onInputChange}>                        <option>PIECE</option>
                        <option>GRAMM</option>
                        <option>KILOGRAMM</option>
                        <option>LITRE</option>
                        <option>DECILITRE</option>
                    </Form.Select>
                </Form.Group>
            </Col>
            <Col>
                <Form.Group className="mb-1" controlId="quantity">
                    <Form.Control placeholder="Quantity" name="amount" value={ingredient.amount} onChange={onInputChange} />
                </Form.Group>
            </Col>
            <Col xs={1}>
                <Button
                    onClick={e => removeIngredient(ingredient)}
                    variant='outline-dark'
                    className="mb-1"
                >x</Button>
            </Col>
        </Row>
    )
}

AddIngredient.propTypes = {};

AddIngredient.defaultProps = {};

export default AddIngredient;