<template>
    <HeaderComponent></HeaderComponent>
    <div class="contact-container">
        <div class="px-5 pt-5" v-if="send">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Send!</strong> Message send success.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"
                    @click="send = false"></button>
            </div>
        </div>
        <div class="px-5 pt-5" v-if="error">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Error!</strong> Message send error.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"
                    @click="error = false"></button>
            </div>
        </div>
        <h1 class="text-center my-5">Contact</h1>
        <div class="row justify-content-center form">
            <div class="col-10 col-lg-6">
                <form>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Email address</label>
                        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                            v-model="email">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Message</label>
                        <textarea type="password" class="form-control" id="exampleInputPassword1"
                            v-model="message"></textarea>
                    </div>
                    <button type="button" class="btn btn-primary" @click="sendMessage">Submit</button>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import HeaderComponent from '../components/HeaderComponent.vue';
import { store } from '../store';
export default {
    name: "Contact",
    components: { HeaderComponent },

    data() {
        return {
            store,
            message: '',
            email: '',
            send: false,
            error: false,
        }
    },

    methods: {
        sendMessage() {
            const data = JSON.stringify({
                email: this.email,
                message: this.message
            })

            axios.post(this.store.apiContactUrl + '/send', data, {
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                }
            }).then((response) => {
                console.log(response.data);
                this.send = true;
                this.email = "";
                this.message = "";
            }).catch(() => {
                this.error = true;
            })

        }
    }
}
</script>

<style lang="scss" scoped>
.contact-container {
    height: calc(100% - 56px);
    overflow-y: auto;
    position: relative;

    .form {
        margin: auto;
        width: 100%;
        position: absolute;
        left: 50%;
        transform: translate(-50%);
    }
}
</style>