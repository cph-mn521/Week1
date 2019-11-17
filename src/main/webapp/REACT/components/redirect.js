import React from 'react'
import { Redirect } from 'react-router-dom'
class redirect extends React.Component {
  state = {
    redirect: true
  }
  renderRedirect = () => {
    if (this.state.redirect) {
      return <Redirect to='CA3/documentation/index.html' />
    }
  }
  render () {
    return (
       <div>
        {this.renderRedirect()}
       </div>
    )
  }
}

export default redirect