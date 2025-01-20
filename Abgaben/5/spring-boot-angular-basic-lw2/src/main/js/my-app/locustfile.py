from locust import HttpUser, task

class Students(HttpUser):
    @task
    def get_students(self):
        self.client.get("/students")
