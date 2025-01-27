import React, { useEffect, useState } from 'react';
import './Browse.css';
import axios from "axios";
import Recipe from "../Recipe/Recipe";
import { Col, Row } from "react-bootstrap";

const baseURL = "http://localhost:8080/api/recipes";

const Browse = () => {
    const [post, setPost] = useState(null);

    useEffect(() => {
        axios.get(baseURL).then((response) => {
            setPost(response.data);
        });
    }, []);

    if (!post) return null;

    return (
        <>
            <Row>
                {post.map((d) => (
                    <Col sm={12} md={6} lg={4} xl={3} key={d.id}>
                        <Recipe
                            id={d.id}
                            title={d.name}
                            description={d.description}
                            image={d.imageUrl}
                        />
                    </Col>
                ))}
            </Row>
        </>
    );
};

Browse.propTypes = {};

Browse.defaultProps = {};

export default Browse;