module.exports = function(grunt) {
    grunt.initConfig({
        jshint: {
            options: {
                jshintrc: '.jshintrc'
            },
            src: [
                'main/**/*.js'
            ]
        },
        uglify: {
            options: {
                preserveComments: false
            },
            dist: {
                files: [{
                    expand: true,
                    cwd: 'main',
                    src: '**/*.js',
                    dest: '../resources/static/min',
                    ext: '.js'
                }]
            }
        },
        concat: {
            js: {
                files: {
                    '../resources/static/dist/app.min.js': [
                        '../resources/static/min/**/*.js'
                    ],
                    '../resources/static/dist/libs.min.js': [
                        'bower_components/jquery/dist/jquery.min.js',
                        'bower_components/angular/angular.min.js',
                        'bower_components/jQuery-SlotMachine/dist/jquery.slotmachine.min.js',
                        'bower_components/angularjs-datepicker/dist/angular-datepicker.min.js'
                    ]
                }
            },
            css: {
                files: {
                    '../resources/static/dist/app.min.css': [
                        '../resources/static/min/**/*.css'
                    ],
                    '../resources/static/dist/libs.min.css': [
                        'bower_components/angular/angular-csp.css',
                        'bower_components/reset-css/reset.css',
                        'bower_components/jQuery-SlotMachine/dist/jquery.slotmachine.min.css',
                        'bower_components/angularjs-datepicker/dist/angular-datepicker.min.css'
                    ]
                }
            }
        },
        clean: {
            before: ['../resources/static'],
            after: ['../resources/static/min']
        },
        copy: {
            main: {
                files: [{
                    expand: true,
                    cwd: 'main',
                    src: [
                        '**',
                        '**/.htaccess',
                        '!**/*.js',
                        '!**/*.scss'
                    ],
                    dest: '../resources/static',
                    filter: 'isFile'
                }]
            },
            mocks: {
                files: [{
                    expand: true,
                    cwd: 'mocks',
                    src: [
                        '**'
                    ],
                    dest: '../resources/static/mocks',
                    filter: 'isFile'
                }]
            }/*,
            icons: {
                files: [{
                    expand: true,
                    cwd: 'bower_components/bootstrap/fonts',
                    src: [
                        '*'
                    ],
                    dest: '../resources/static/fonts'
                }]
            }*/
        },
        sass: {
            options: {
                outputStyle: 'compressed',
                sourceMap: false
            },
            dist: {
                files: [{
                    expand: true,
                    cwd: 'main',
                    src: '**/*.scss',
                    dest: '../resources/static/min',
                    ext: '.css'
                }]
            }
        },
        postcss: {
            options: {
                map: false,
                processors: [
                    require('autoprefixer')({
                        browsers: ['last 2 versions']
                    })
                ]
            },
            dist: {
                src: '../resources/static/**/*.css'
            }
        },
        watch: {
            main: {
                files: ['main/**'],
                tasks: ['build'],
                options: {
                    atBegin: true
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-sass');
    grunt.loadNpmTasks('grunt-postcss');

    grunt.registerTask('default', ['watch']);
    grunt.registerTask('build-js', ['jshint', 'uglify', 'concat:js']);
    grunt.registerTask('build-css', ['sass', 'postcss', 'concat:css']);
    grunt.registerTask('build', ['clean:before', 'copy', 'build-js', 'build-css', 'clean:after']);
};
