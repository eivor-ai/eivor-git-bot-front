import React, { Fragment } from 'react';
import Form from './Form';
import Integrations from './Integrations';

export default function Dashboard() {
  return (
    <Fragment>
      <Form />
      <Integrations />
    </Fragment>
  )
}
