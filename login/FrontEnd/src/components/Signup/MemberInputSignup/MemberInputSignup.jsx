'use client'
import React, { useState } from 'react';
import axios from 'axios';
import styles from './MemberInputSignup.module.scss';
import { useRouter } from 'next/navigation';
import { Button, Modal, Space } from 'antd';

const MemberInputSignup = () => {
    const [username, setUsername] = useState('');
    const [userid, setUserid] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [nickname, setNickname] = useState('');
    const [email, setEmail] = useState('');
    const [phonenum, setPhoneNumber] = useState('');
    const [errors, setErrors] = useState({});


    const backendUrl = 'https://i10a207.p.ssafy.io:80/api'

    const navigate = useRouter();

    // 이름 유효성 검사
    const validateUsername = (value) => {
        if (value.length > 15) {
        // if (value.length < 3 || value.length > 15) {
          setErrors((prevErrors) => ({
            ...prevErrors,
            username: '* 사용자명은 15자 이하이어야 합니다.',
          }));
        } else {
          setErrors((prevErrors) => ({ ...prevErrors, username: null }));
        }
    };

    // 아이디 유효성 검사
    const validateUserid = (value) => {
        if (value.length < 3 || value.length > 15) {
          setErrors((prevErrors) => ({
            ...prevErrors,
            userid: '* 아이디는 3자 이상 15자 이하이어야 합니다.',
          }));
        } else {
          setErrors((prevErrors) => ({ ...prevErrors, userid: null }));
        }
    };

    // 비밀번호
    const validatePassword = (value) => {
        if (value.length < 6) {
          setErrors((prevErrors) => ({
            ...prevErrors,
            password: '* 비밀번호는 최소 6자 이상이어야 합니다.',
          }));
        } else {
          setErrors((prevErrors) => ({ ...prevErrors, password: null }));
        }
    };

    // 비밀번호 확인
    const validateConfirmPassword = (value) => {
        if (value !== password || value.trim() === '') {
          setErrors((prevErrors) => ({
            ...prevErrors,
            confirmPassword: '* 비밀번호가 일치하지 않습니다.',
          }));
        } else {
          setErrors((prevErrors) => ({ ...prevErrors, confirmPassword: null }));
        }
    };

    // 이메일 유효성 검사
    const validateEmail = (value) => {
        if (!/\S+@\S+\.\S+/.test(value) || value.trim() === '') {
          setErrors((prevErrors) => ({ ...prevErrors, email: '* 올바른 형식의 이메일을 입력하세요.' }));
        } else {
          setErrors((prevErrors) => ({ ...prevErrors, email: null }));
        }
      };

    // 회원가입 성공 모달
    const success = () => {
      Modal.success({
        content: '회원가입이 완료되었습니다!',
        onOk: () => {
          navigate.push('/LoginPage');  // 로그인 페이지로 이동
        },
      });
    };

    const signUpSubmit = async (e) => {
      e.preventDefault();

      // 유효성 검사 실패한 경우
      if (!Object.values(errors).every((error) => error === null)) {
        console.log('모든 항목을 올바르게 입력하세요.');
        return;
      }
      
      const data = {
        "memberName": username,
        "memberId": userid,
        "memberPwd": password,
        "memberNickname": nickname,
        "memberEmail": email,
        "memberContact": phonenum,
        "memberPoint": 0,
        "memberIsDeleted" : false,
        "memberCreatedDate" : '2024-01-29T04:54:33'
       }
 

      try {
        const response = await axios.
          post(`${backendUrl}/members/new`, data, {
            headers: {
              'Content-Type': 'application/json;  charset=UTF-8',
            },
          });
        navigate.push('/');
        console.log(response.data);
        
        
      } catch (error) {
        console.error(error);
      }

    
    
    };

    return (
      <div>
        <form className={styles.form} onSubmit={signUpSubmit}>
            <div className={styles.form_title}>개인 회원가입</div>
            <div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>이름</div>
                    <input type="name" className={styles.form_input} placeholder='이름' 
                    value={username} onChange={(e) => {
                        setUsername(e.target.value);
                        validateUsername(e.target.value);
                    }}/>
                    {errors.username && <span className={styles.error}>{errors.username}</span>}
                </div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>아이디</div>
                    <div className={styles.form_inputbtn}>
                        <input type="text" className={styles.form_input_3} placeholder='아이디'
                        value={userid} onChange={(e) => {
                            setUserid(e.target.value);
                            validateUserid(e.target.value);
                        }}/>
                        <button className={styles.input_btn}>중복 확인</button>
                    </div>
                    {errors.userid && <span className={styles.error}>{errors.userid}</span>}
                </div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>비밀번호</div>
                    <input type="password" className={styles.form_input} placeholder='비밀번호'
                    value={password} onChange={(e) => {
                        setPassword(e.target.value);
                        validatePassword(e.target.value);
                    }}/>
                    {errors.password && <span className={styles.error}>{errors.password}</span>}
                </div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>비밀번호 확인</div>
                    <input type="password" className={styles.form_input} placeholder='비밀번호 확인'
                    value={confirmPassword} onChange={(e) => {
                        setConfirmPassword(e.target.value);
                        validateConfirmPassword(e.target.value);
                    }}/>
                    {errors.confirmPassword && <span className={styles.error}>{errors.confirmPassword}</span>}
                </div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>닉네임</div>
                    <input type="name" className={styles.form_input} placeholder='닉네임'
                    value={nickname} onChange={(e) => {
                      setNickname(e.target.value);
                  }}/>
                </div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>이메일</div>
                    <input type="text" className={styles.form_input} placeholder='이메일'
                    value={email} onChange={(e) => {
                        setEmail(e.target.value);
                        validateEmail(e.target.value);
                    }}/>
                    {errors.email && <span className={styles.error}>{errors.email}</span>}
                </div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>연락처</div>
                    <div className={styles.form_inputbtn}>
                    <input style={{ width: '200px' }} type="text" className={styles.form_input} placeholder='연락처'
                    value={phonenum} onChange={(e) => {
                        setPhoneNumber(e.target.value);
                    }}/>
                        <button style={{ paddingLeft: '1opx'}} className={styles.input_btn}>인증하기</button>
                    </div>
                </div>
            </div>
            <div>
                <button type="submit" className={styles.submit_btn} onClick={success}>가입하기</button>
            </div>
        </form>
        
      </div>
    )
}

export default MemberInputSignup
