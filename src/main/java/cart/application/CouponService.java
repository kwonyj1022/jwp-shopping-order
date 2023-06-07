package cart.application;

import cart.domain.Coupon;
import cart.domain.Member;
import cart.dto.coupon.CouponResponse;
import cart.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class CouponService {

    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public List<CouponResponse> findByMember(Member member) {
        List<Coupon> coupons = couponRepository.findByMemberId(member.getId());
        return coupons.stream().map(CouponResponse::of).collect(toList());
    }
}
