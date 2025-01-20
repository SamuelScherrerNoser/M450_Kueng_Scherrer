describe('Add Student Test', () => {
  it('adds new student.', () => {
    cy.visit('/addstudents')
    cy.get('input[name=name]').type('test')
    cy.get('input[name=email]').type('test@mail.com{enter}')
    cy.contains('Submit').click()
  })
})
