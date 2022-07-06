import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import StudentView from '../views/StudentView.vue'
import LoginView from '../views/LoginView.vue'
import StaffView from '../views/StaffView.vue'
import AddStudentView from '../views/AddStudentView.vue'
import CoursesView from '../views/CoursesView.vue'
import AddStaffView from '../views/AddStaffView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    }
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/student',
    name: 'student',
    component: StudentView
  },
  {
    path: '/staff',
    name: 'staff',
    component: StaffView
  } ,
 {
   path: '/addStaff',
   name: 'addStaff',
   component: AddStaffView
 } ,
  {
    path: '/addstudent',
    name: 'addstudent',
    component: AddStudentView
  } ,
  {
    path: '/courses',
    name: 'courses',
    component: CoursesView
  } 
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
