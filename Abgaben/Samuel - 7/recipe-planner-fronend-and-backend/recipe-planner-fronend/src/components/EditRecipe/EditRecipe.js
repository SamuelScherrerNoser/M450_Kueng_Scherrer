import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import axios from 'axios';
import { Button, Form } from 'react-bootstrap';

function EditRecipe() {
    const { recipeId } = useParams(); // Rezept-ID aus der URL holen
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: '',
        description: '',
        imageUrl: '',
    });

    const { register, handleSubmit, setValue, formState: { errors } } = useForm();

    // Rezept laden, wenn wir in der Bearbeitungsansicht sind
    useEffect(() => {
        if (recipeId) {
            axios.get(`http://localhost:8080/api/recipes/recipe/${recipeId}`)
                .then(response => {
                    const recipe = response.data;
                    setFormData(recipe);
                    // Setze die Werte ins Formular
                    setValue('name', recipe.name);
                    setValue('description', recipe.description);
                    setValue('imageUrl', recipe.imageUrl);
                })
                .catch(error => {
                    console.error('Error fetching recipe:', error);
                });
        }
    }, [recipeId, setValue]);

    // Formular absenden
    const onSubmit = async (data) => {
        const recipeData = { ...data };

        try {
            const response = await axios.put(
                `http://localhost:8080/api/recipes/recipe/${recipeId}`,
                recipeData
            );
            console.log('Success:', response.data);
            navigate('/');
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div className="bg">
            <div className="m-3">
                <h1 className="h3 bg-dark text-bg-primary mt-2">Edit Recipe</h1>
                <Form onSubmit={handleSubmit(onSubmit)}>
                    <Form.Group className="mb-1" controlId="formBasicName">
                        <Form.Label>Recipe Name:</Form.Label>
                        <Form.Control
                            placeholder="Name"
                            {...register('name', { required: true })}
                            defaultValue={formData.name}
                        />
                        {errors.name && <p className="error">Name is required</p>}
                    </Form.Group>

                    <Form.Group className="mb-1" controlId="formBasicDescription">
                        <Form.Label>Description:</Form.Label>
                        <Form.Control
                            placeholder="Description"
                            {...register('description', { required: true })}
                            defaultValue={formData.description}
                        />
                        {errors.description && <p className="error">Description is required</p>}
                    </Form.Group>

                    <Form.Group className="mb-1 mb-5" controlId="formBasicImageUrl">
                        <Form.Label>Image URL:</Form.Label>
                        <Form.Control
                            placeholder="URL"
                            {...register('imageUrl', { required: true })}
                            defaultValue={formData.imageUrl}
                        />
                        {errors.imageUrl && <p className="error">Image URL is required</p>}
                    </Form.Group>

                    <Button variant="primary" type="submit" className="mb-5">
                        Submit
                    </Button>
                </Form>
            </div>
        </div>
    );
}

export default EditRecipe;