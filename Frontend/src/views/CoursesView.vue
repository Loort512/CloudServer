<template>
    <div class="courses">
        <div class="header">
            <router-link v-if="this.$store.state.loggedInAs === 'admin'" to="/student" >Student</router-link> |  
            <router-link v-if="this.$store.state.loggedInAs === 'admin'" to="/staff" >Staff</router-link><br>
            <router-link v-if="this.$store.state.loggedInAs === 'staff'" to="/courses" >Courses</router-link>
        </div>
        <h1>Course Overview</h1>
        <table border=0>
            <tr v-for="course in courses">
                  <td> <button class="btnShowCourse" @click="show(course)" > {{ course.name }} </button></td>
            </tr>
        </table>

        <div v-if="showCourseInfo" class="courseInfo">
            <h1>Students</h1>
            <table border=0>
            <tr v-for="student in activeCourse.students">
                  <td> {{ student.name }} </td>
            </tr>
        </table>
        </div>

    </div>
</template>

<script>

export default{
    name: 'CoursesView',
    data(){
        return{
           courses:[] ,
           showCourseInfo: false,
           activeCourse: {} 
        }  
    }, 
    created(){
        let math ={
            id: '1',
            name:'Mathematics', 
            students:[
            {name: 'John Dillan'},{name: 'Max Muster'} ,{name: 'Felix Bruch'} 
            ]  
        } 
        let physics ={
            id: '2',
            name:'Physics', 
            students:[
            {name: 'John Dillan'},{name: 'Moni Muster'}  
            ]  
        } 
        this.courses.push(math);
        this.courses.push(physics);
    } ,
    components:{
     
    } ,
    methods:{
      show(course){
          if(this.showCourseInfo){
            this.activeCourse = undefined;
            this.showCourseInfo = false;
          } else{
            console.log("show Course info: ", course)
            this.activeCourse =  course;
            this.showCourseInfo = true;
          } 
      } 
    }  
} 
</script>
<style scoped>
.btnShowCourse{
    width: 100%
} 
</style>
